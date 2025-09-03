package com.ati4.training.core.models.v1;

import com.day.cq.wcm.api.Page;
import org.osgi.annotation.versioning.ConsumerType;

import java.util.List;

@ConsumerType
public interface BreadcrumbModel {

    String RESOURCE_TYPE = "/apps/ati4-training/components/breadcrumb/v1/breadcrumb";

    List<Page> breadcrumbPages();

}
