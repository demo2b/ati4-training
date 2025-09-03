package com.ati4.training.core.impl;

import com.ati4.training.core.models.v1.NavigationModel;
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
        adapters = NavigationModel.class,
        resourceType = NavigationModel.RESOURCE_TYPE
)
public class NavigationModelImpl implements NavigationModel {

    @ScriptVariable
    private Page currentPage;

    final private List<Page> navigationPages = new ArrayList<>();

    @PostConstruct
    protected void postConstruct() {
        // Retrieving langauge root page  eg:'/content/ati4/es-MX'
        Page rootPage = currentPage.getAbsoluteParent(2);

        // Iterating over subpages having child pages
        Iterator<Page> rootPages = rootPage.listChildren();
        while (rootPages.hasNext()) {
            Page subPage = rootPages.next();
            if(subPage.listChildren().hasNext()) {
                navigationPages.add(subPage);
            }
        }
    }

    @Override
    public List<Page> navigationPages() {
        return navigationPages;
    }
}
