package org.leoho.domain;

import org.leoho.interfaces.Reportable;

public class Admin extends User implements Reportable {

    public Admin(String name) {
        super(name);
    }

    @Override
    public void generateReport() {

    }
}
