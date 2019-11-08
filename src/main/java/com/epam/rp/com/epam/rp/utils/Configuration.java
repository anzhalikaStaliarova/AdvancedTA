package com.epam.rp.com.epam.rp.utils;


import org.apache.commons.configuration2.ImmutableConfiguration;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.configuration2.interpol.Lookup;
import org.apache.commons.configuration2.sync.SynchronizerSupport;

import java.util.Collection;
import java.util.Map;

public interface Configuration extends ImmutableConfiguration, SynchronizerSupport {
    Configuration subset(String var1);

    void addProperty(String var1, Object var2);

    void setProperty(String var1, Object var2);

    void clearProperty(String var1);

    void clear();

    ConfigurationInterpolator getInterpolator();

    void setInterpolator(ConfigurationInterpolator var1);

    void installInterpolator(Map<String, ? extends Lookup> var1, Collection<? extends Lookup> var2);
}
