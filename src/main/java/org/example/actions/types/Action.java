package org.example.actions.types;

public class Action {

    private boolean cancel = false;

    public boolean isCancelled() {
        return this.cancel;
    }

    public void setCancelled() {
        this.cancel = true;
    }
}
