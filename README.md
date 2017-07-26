# BreadCrumbSpringAnnotation
# An untested BreadCrumbSpringAnnotation, use it smart and feel free to send me pull requests
If BreadCrumbs is static, I can create an sitemap, and generate it. But if not (for example, I need to display topic title as part of BreadCrumbs), that's why this annotation comes out


# How it work
1, add @BreadCrumb to @Controller methods
2, BreadCrumbScanner will scan all methods with @BreadCrumb annotation, and save as registeredBreadCrumbs
3, In BreadCrumbInterceptor, it will build BreadCrumbs.




