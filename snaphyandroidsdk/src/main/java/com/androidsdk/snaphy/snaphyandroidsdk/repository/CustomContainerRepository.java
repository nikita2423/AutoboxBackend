package com.androidsdk.snaphy.snaphyandroidsdk.repository;

import com.androidsdk.snaphy.snaphyandroidsdk.models.CustomContainer;
import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.RestRepository;
import com.strongloop.android.loopback.callbacks.JsonArrayParser;
import com.strongloop.android.loopback.callbacks.JsonObjectParser;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.loopback.callbacks.ObjectCallback;
import com.strongloop.android.remoting.adapters.Adapter;
import com.strongloop.android.remoting.adapters.RestContract;
import com.strongloop.android.remoting.adapters.RestContractItem;

/**
 * Created by Ravi-Gupta on 2/16/2016.
 */
public class CustomContainerRepository extends RestRepository<CustomContainer> {
    public CustomContainerRepository(){
        super("CustomContainer", CustomContainer.class);
    }

    private String getNameForRestUrl() {
        return "containers";
    }
    /**
     * Creates a {@link RestContract} representing the user type's custom
     * routes. Used to extend an {@link Adapter} to support user. Calls
     * super {@link ModelRepository} createContract first.
     *
     * @return A {@link RestContract} for this model type.
     */

    public RestContract createContract() {
        RestContract contract = super.createContract();

        String className = getClassName();

        final String basePath = "/" + getNameForRestUrl();
        contract.addItem(new RestContractItem(basePath, "POST"),
                className + ".create");

        contract.addItem(new RestContractItem(basePath, "GET"),
                className + ".getAll");

        contract.addItem(new RestContractItem(basePath + "/:name", "GET"),
                className + ".get");

        contract.addItem(new RestContractItem(basePath + "/:name", "DELETE"),
                className + ".prototype.remove");

        return contract;
    }

    /**
     * Create a new container.
     * @param name The name of the container, must be unique.
     * @param callback The callback to be executed when finished.
     */
    public void create(String name, ObjectCallback<CustomContainer> callback) {
        invokeStaticMethod("create", ImmutableMap.of("name", name),
                new JsonObjectParser<CustomContainer>(this, callback));
    }

    /**
     * Get a named container
     * @param containerName The container name.
     * @param callback The callback to be executed when finished.
     */
    public void get(String containerName, ObjectCallback<CustomContainer> callback) {
        invokeStaticMethod("get", ImmutableMap.of("name", containerName),
                new JsonObjectParser<CustomContainer>(this, callback));
    }

    /**
     * List all containers.
     * @param callback The callback to be executed when finished.
     */
    public void getAll(ListCallback<CustomContainer> callback) {
        invokeStaticMethod("getAll", null,
                new JsonArrayParser<CustomContainer>(this, callback));
    }

}