package com.ati4.training.core.impl.v1;

import com.ati4.training.core.models.v1.PushImageEdito;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(
    adaptables = Resource.class,
    adapters = PushImageEdito.class,
    resourceType = PushImageEdito.RESOURCE_TYPE
)
public class PushImageEditoImpl implements PushImageEdito {

    @ValueMapValue
    private String mode;

    @ChildResource
    private Resource accordion;

    final private List<EditoItem> editoItems = new ArrayList<>();

    @PostConstruct
    protected void postConstruct() {
        if(mode != null && mode.equals("accordion")) {
            if(accordion != null && accordion.listChildren().hasNext()) {
                Iterator<Resource> iterator = accordion.listChildren();
                while(iterator.hasNext()) {
                    Resource itemResource = iterator.next();
                    ValueMap vm = itemResource.getValueMap();
                    PushImageEdito.EditoItem editoItem = new PushImageEdito.EditoItem(
                        vm.get("title", String.class),
                        vm.get("text", String.class),
                        vm.get("headingLevel", String.class)
                    );
                    editoItems.add(editoItem);
                }
            }
        }
    }

    @Override
    public List<EditoItem> editoItems() {
        return editoItems;
    }
}
