package io.easyspring.service.message.support;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息接收者对象
 *
 * @author summer
 * DateTime 2019-03-19 12:51
 * @version V1.0.0-RELEASE
 */
@AllArgsConstructor
@Data
public class EasyMessageReceiver implements Serializable {

    private static final long serialVersionUID = 3272762485998650809L;
}
