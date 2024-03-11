package com.tdtech.scorecardapi.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    final String resourceName;

    public ResourceNotFoundException(String id, String resourceName) {
        super(String.format("Unable to retrieve %s identified by [%s]", resourceName, id));
        this.resourceName = resourceName;
    }

}
