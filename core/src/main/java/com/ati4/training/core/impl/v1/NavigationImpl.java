package com.ati4.training.core.impl.v1;

import com.ati4.training.core.models.v1.Navigation;
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
    adapters = Navigation.class,
    resourceType = Navigation.RESOURCE_TYPE
)
public class NavigationImpl implements Navigation {

    @ScriptVariable
    private Page currentPage;

    private final List<Page> navigationPages = new ArrayList<>();

    @PostConstruct
    protected void postConstruct() {
        // Retrieving language root page  eg:'/content/ati4/es-MX'
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
