# BreadCrumbSpringAnnotation
# An untested BreadCrumbSpringAnnotation, use it smartly and feel free to send me pull requests
If BreadCrumbs is static, I can create a sitemap. But if dynamic (for example, I need to display topic title as part of BreadCrumbs), that's why this annotation comes out


# How it work

### add @BreadCrumb to @Controller methods
### BreadCrumbScanner will scan all methods with @BreadCrumb annotation, and save as registeredBreadCrumbs
### In BreadCrumbInterceptor, it will build BreadCrumbs.




