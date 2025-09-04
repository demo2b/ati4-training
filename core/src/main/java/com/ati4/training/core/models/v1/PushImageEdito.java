package com.ati4.training.core.models.v1;

import com.day.cq.wcm.api.Page;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.osgi.annotation.versioning.ConsumerType;

import java.util.List;

@ConsumerType
public interface PushImageEdito {

    String RESOURCE_TYPE = "/apps/ati4-training/components/push-image-edito/v1/push-image-edito";

    List<EditoItem> editoItems();

    class EditoItem {

        private final String title;
        private final String text;
        private final String headingLevel;

        public EditoItem(String title, String text, String headingLevel) {
            this.title = title;
            this.text = text;
            this.headingLevel = headingLevel;
        }

        public String getTitle() { return this.title; }
        public String getText() { return this.text; }
        public String getHeadingLevel() { return this.headingLevel; }
    }
}

