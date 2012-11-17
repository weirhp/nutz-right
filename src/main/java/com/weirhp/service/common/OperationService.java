package com.weirhp.service.common;

import java.util.List;
import java.util.Map;

import com.weirhp.domain.common.MenuOperation;
import com.weirhp.domain.common.Operation;

public interface OperationService {
    public List<Operation> findAllOperation();

    List<Map<String, Object>> findAllOperationForTree();

    /**
     * 查询一个菜单关联的操作
     * @param menuId
     * @return
     */
    List<MenuOperation> findMenuOperation(Long menuId);
    
    /**
     * 查询一个角色关联的operation
     * @param menuId
     * @return
     */
    Map<String, Object> findMenuOperationForRole(Long roleId);

    /**
     * 保存菜单关联的operation
     * @param menuId
     * @param operationIds
     * @return
     */
    boolean saveMenuOperations(Long menuId, Long[] operationIds);
    
}
