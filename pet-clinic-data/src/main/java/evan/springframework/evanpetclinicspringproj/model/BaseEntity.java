package evan.springframework.evanpetclinicspringproj.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    //这里要用Long而不是primitive type的long，因为Long可以是Null但是long不行
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
