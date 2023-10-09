package battle2023.ucp.Entities;

import java.util.ArrayList;
import java.util.List;

abstract class Filter {
    protected String name;

    public Filter(String name) {
        this.name = name;
    }

    public abstract List<Email> filter(List<Email> emails);
}
