package com.sven.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sven.annotation.BreadCrumb;
import com.sven.model.BreadCrumbModel;
import com.sven.service.BreadCrumbService;

@Component
public class BreadCrumbInterceptor extends HandlerInterceptorAdapter
{

    @Autowired
    private BreadCrumbService breadCrumbService;

    private static final String BREAD_CRUMB_LINKS = "breadCrumb";

    @Override
    public void postHandle(final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler, final ModelAndView modelAndView) throws Exception
    {
        super.postHandle(request, response, handler, modelAndView);

        HttpSession session = request.getSession();

        Annotation[] declaredAnnotations = getDeclaredAnnotationsForHandler(handler);

        for (Annotation annotation : declaredAnnotations)
        {
            if (annotation instanceof BreadCrumb)
            {
                processBreadCrumb(modelAndView, session, (BreadCrumb) annotation);
            }
        }
    }

    private void processBreadCrumb(final ModelAndView modelAndView,
            final HttpSession session,
            final BreadCrumb breadCrumb)
    {

        StandardEvaluationContext context =
                new StandardEvaluationContext(modelAndView.getModel());
        context.addPropertyAccessor(new MapAccessor());
        ExpressionParser parser = new SpelExpressionParser();

        BreadCrumbModel breadCrumbModel =
                this.solveBreadCrum(breadCrumb.name(), parser, context);

        modelAndView.addObject(BREAD_CRUMB_LINKS, breadCrumbModel);
    }

    private BreadCrumbModel solveBreadCrum(final String name,
            final ExpressionParser parser, final StandardEvaluationContext context)
    {

        BreadCrumb breadCrumb =
                breadCrumbService.getParentRegisteredBreadCrumb(name);

        String key =
                parser.parseExpression(breadCrumb.key()).getValue(context, String.class);

        String label =
                parser.parseExpression(breadCrumb.label()).getValue(context,
                        String.class);

        BreadCrumbModel parent = null;
        if (!StringUtils.isEmpty(breadCrumb.parent()))
        {

            String parentKey =
                    parser.parseExpression(breadCrumb.parentKey()).getValue(context,
                            String.class);

            String parentUid = breadCrumb.parent() + "-" + parentKey;
            parent = breadCrumbService.getBreadCrumbModels(parentUid);

            if (parent == null)
            {

                // the model is supposed to contain some attributes in order to solve
                // parent
                parent = this.solveBreadCrum(breadCrumb.parent(), parser, context);
            }
        }
        BreadCrumbModel breadCrumbModel =
                BreadCrumbModel.builder().withKey(key).withLabel(label).withName(
                        breadCrumb.name()).withParent(parent).build();

        breadCrumbService.addBreadCrumbModels(breadCrumbModel);

        return breadCrumbModel;
    }

    private Annotation[] getDeclaredAnnotationsForHandler(final Object handler)
    {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        return declaredAnnotations;
    }
}
