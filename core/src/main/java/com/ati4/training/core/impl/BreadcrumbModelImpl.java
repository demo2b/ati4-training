package com.ati4.training.core.impl;

import com.ati4.training.core.models.v1.BreadcrumbModel;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(
    adaptables = SlingHttpServletRequest.class,
    adapters = BreadcrumbModel.class,
    resourceType = BreadcrumbModel.RESOURCE_TYPE
)
public class BreadcrumbModelImpl implements BreadcrumbModel {

    @ScriptVariable
    private Page currentPage;

    final private List<Page> breadcrumbPages = new ArrayList<>();

    @PostConstruct
    protected void postConstruct() {
        // Retrieving langauge root page  eg:'/content/ati4/es-MX'
        int depth = currentPage.getDepth();
        if(depth >= 3) {
            Page parentPage = currentPage;
            while (parentPage.getDepth() > 2) {
                breadcrumbPages.add(parentPage);
                parentPage = parentPage.getParent();
            }
        }
    }

    @Override
    public List<Page> breadcrumbPages() {
        return breadcrumbPages;
    }
}
