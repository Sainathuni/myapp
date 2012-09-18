package org.saibaba.fw.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;


public class StringUtils extends org.apache.commons.lang.StringUtils
{
    private static final char AMPERSAND = '&';
    private static final char SEMICOLON = ';';

    @SuppressWarnings("rawtypes")
    private static final Map replacementMap = new HashMap();

    static
    {
        replacementMap.put("amp", "&");
        replacementMap.put("gt", ">");
        replacementMap.put("lt", "<");
        replacementMap.put("quot", "\"");
        replacementMap.put("#39", "\\");
    }

   
   
}
