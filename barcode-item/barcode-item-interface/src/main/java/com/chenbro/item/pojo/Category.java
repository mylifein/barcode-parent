package com.chenbro.item.pojo;

        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.Table;

/**
 * @ClassName Category
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/16 11:13
 * @Version 1.0
 **/
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;       // 注意isParent 生成的getter 和 setter 方法需要手动加上Is
    private Integer sort;

    // getter 和setter 省略


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
