/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.api.naming.listener;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * Naming Event.
 *
 * @author nkorange
 */
public class NamingEvent implements Event {
    
    private String serviceName;
    
    private String groupName;
    
    private String clusters;
    
    private List<Instance> instances;
    
    private final boolean fromServer;
    
    public NamingEvent(String serviceName, List<Instance> instances) {
        this(serviceName, null, null, instances);
    }
    
    public NamingEvent(String serviceName, String groupName, String clusters,
        List<Instance> instances) {
        this(serviceName, groupName, clusters, instances, false);
    }
    
    /**
     * Create an event with its notification origin.
     *
     * @param serviceName service name
     * @param groupName group name
     * @param clusters subscribed clusters
     * @param instances current instances
     * @param fromServer whether this notification was triggered by a server push
     */
    public NamingEvent(String serviceName, String groupName, String clusters,
        List<Instance> instances, boolean fromServer) {
        this.serviceName = serviceName;
        this.groupName = groupName;
        this.clusters = clusters;
        this.instances = instances;
        this.fromServer = fromServer;
    }
    
    /**
     * Whether this notification was triggered by a server push.
     *
     * <p>Subscription responses, local cache replay, polling and failover notifications return
     * {@code false}, as do events created with the legacy constructors. This flag describes the
     * notification path, not the origin of the instance data or the listener's first callback.
     * Either value may carry changes not yet processed by the application.
     *
     * @return {@code true} for a server-push notification
     */
    public boolean isFromServer() {
        return fromServer;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public List<Instance> getInstances() {
        return instances;
    }
    
    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }
    
    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getClusters() {
        return clusters;
    }
    
    public void setClusters(String clusters) {
        this.clusters = clusters;
    }
}
