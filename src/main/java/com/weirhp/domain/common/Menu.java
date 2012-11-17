package com.weirhp.domain.common;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.json.JsonField;

import com.weirhp.domain.BaseObject;

@Table("menu")
public class Menu extends BaseObject {
    
    private static final long serialVersionUID = 68368647654813427L;
    
    @Id(auto = true)
    private Long              id;
    @Column("name")
    private String            name;
    @Column
    @Comment("对应的访问链接")
    @JsonField("accessUrl")
    private String            url;
    @Column("parent_id")
    @Comment("父结点ID")
    @JsonField("pId")
    private Long              parentId;
    @Column
    @Comment("排序字段")
    private Integer           indexs;
    
    @Column("show_menu")
    @Comment("是否显示该菜单")
    private Integer           showMenu;
    
    /**
     * 是否被选中
     */
    private boolean           checked;
    
    /**
     * 临时使用的一个String id
     */
    private String            tmpId;
    
    /**
     * 临时使用的一个String pId
     */
    private String            pId;
    
    private List<Operation>   operations;
    
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
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Long getParentId() {
        return parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    public Integer getIndexs() {
        return indexs;
    }
    
    public void setIndexs(Integer indexs) {
        this.indexs = indexs;
    }
    
    public String getTmpId() {
        return tmpId;
    }
    
    public void setTmpId(String tmpId) {
        this.tmpId = tmpId;
    }
    
    public String getpId() {
        return pId;
    }
    
    public void setpId(String pId) {
        this.pId = pId;
    }
    
    public List<Operation> getOperations() {
        return operations;
    }
    
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
    
    public boolean isChecked() {
        return checked;
    }
    
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getShowMenu() {
        return showMenu;
    }

    public void setShowMenu(Integer showMenu) {
        this.showMenu = showMenu;
    }
    
}
