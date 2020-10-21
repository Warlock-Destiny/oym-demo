package com.oym.activiti.custom.cmd;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntityManager;

import java.io.Serializable;

/**
 * @author zhangyd
 * @date 2019/12/25 16:43
 * @desc
 */
public class HistoryTaskCmd implements Command<Object>, Serializable {

    private static final long serialVersionUID = 1L;
    protected String taskId;

    public HistoryTaskCmd(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public Object execute(CommandContext commandContext) {
        if (commandContext.getHistoryManager().isHistoryEnabled()) {
            HistoricTaskInstanceEntityManager historicTaskInstanceDataManager = commandContext.getHistoricTaskInstanceEntityManager();
            HistoricTaskInstanceEntity historicTaskInstance = historicTaskInstanceDataManager.findById(taskId);
            if (historicTaskInstance != null) {
                commandContext.getHistoricDetailEntityManager().deleteHistoricDetailsByTaskId(taskId);
                commandContext.getHistoricVariableInstanceEntityManager().deleteHistoricVariableInstancesByTaskId(taskId);
                commandContext.getAttachmentEntityManager().deleteAttachmentsByTaskId(taskId);
                commandContext.getHistoricIdentityLinkEntityManager().deleteHistoricIdentityLinksByTaskId(taskId);
                historicTaskInstanceDataManager.delete(historicTaskInstance);
            }
        }
        return null;
    }
}
