package com.sven.model;

import javax.annotation.Generated;

public class BreadCrumbModel
{

    private String name;

    private String key;

    private String label;

    private BreadCrumbModel parent;
    
    public String getUid() {
        return this.name + "-" + this.getKey();
    }

    @Generated("SparkTools")
    private BreadCrumbModel(final Builder builder)
    {
        this.name = builder.name;
        this.key = builder.key;
        this.label = builder.label;
        this.parent = builder.parent;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(final String key)
    {
        this.key = key;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(final String label)
    {
        this.label = label;
    }

    public BreadCrumbModel getParent()
    {
        return parent;
    }

    public void setParent(final BreadCrumbModel parent)
    {
        this.parent = parent;
    }

    /**
     * Creates builder to build {@link BreadCrumbModel}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Builder to build {@link BreadCrumbModel}.
     */
    @Generated("SparkTools")
    public static final class Builder
    {
        private String name;
        private String key;
        private String label;
        private BreadCrumbModel parent;

        private Builder()
        {
        }

        public Builder withName(final String name)
        {
            this.name = name;
            return this;
        }

        public Builder withKey(final String key)
        {
            this.key = key;
            return this;
        }

        public Builder withLabel(final String label)
        {
            this.label = label;
            return this;
        }

        public Builder withParent(final BreadCrumbModel parent)
        {
            this.parent = parent;
            return this;
        }

        public BreadCrumbModel build()
        {
            return new BreadCrumbModel(this);
        }
    }

}
