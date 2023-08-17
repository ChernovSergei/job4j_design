package ru.job4j.question;

public class Info {

    private int added;
    private int changed;
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    public void incrementAdded() {
        added++;
    }

    public void incrementChanged() {
        changed++;
    }

    public void incrementDeleted() {
        deleted++;
    }

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Info)) {
            return false;
        }
        Info info = (Info) o;
        return added == info.getAdded() && changed == info.getChanged() & deleted == info.getDeleted();
    }

    @Override
    public int hashCode() {
        int result;
        result = added;
        result = result * 31 + changed;
        result = result * 31 + deleted;
        return result;
    }
}
