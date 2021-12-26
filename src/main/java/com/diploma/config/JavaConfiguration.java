package com.diploma.config;

public class JavaConfiguration implements Configuration {
    @Override
    public String getPackageToScan() {
        return "com.diploma";
    }
}
