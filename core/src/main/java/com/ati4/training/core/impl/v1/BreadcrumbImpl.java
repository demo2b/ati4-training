package com.ati4.training.core.impl.v1;

import com.ati4.training.core.models.v1.Breadcrumb;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(
    adaptables = SlingHttpServletRequest.class,
    adapters = Breadcrumb.class,
    resourceType = Breadcrumb.RESOURCE_TYPE
)
public class BreadcrumbImpl implements Breadcrumb {

    @ScriptVariable
    private Page currentPage;

    final private List<Page> breadcrumbPages = new ArrayList<>();

    @PostConstruct
    protected void postConstruct() {
        // Retrieving langauge root page  eg:'/content/ati4/es-MX'
        int depth = currentPage.getDepth();
        // If the current page is deep enough, creating breadcrumb items eg: '/content/ati4/es-MX/the-company/our-compromises.html'
        if(depth >= 5) {
            Page parentPage = currentPage;
            // Going upper until we reach langauge page eg:'/content/ati4/es-MX'
            while (parentPage.getDepth() > 2) {
                // We want to exclude 'folder' pages like '/content/ati4/es-MX/the-company' because it should not appear
                // in breadcrumb elements
                if(parentPage.getDepth() != 4) {
                    breadcrumbPages.add(parentPage);
                }
                parentPage = parentPage.getParent();
            }
        }
        Collections.reverse(breadcrumbPages);
    }

    @Override
    public List<Page> breadcrumbPages() {
        return breadcrumbPages;
    }
}
