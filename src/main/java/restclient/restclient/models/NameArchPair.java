package restclient.restclient.models;

import java.util.Objects;

public class NameArchPair {
    private final String name;
    private final String arch;
    private int hashCode;

    public NameArchPair (String name, String arch) {
        this.name = name;
        this.arch = arch;
        this.hashCode = Objects.hash(name, arch);
    }

    public String getName () {
        return name;
    }

    public String getArch () {
        return arch;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;

        NameArchPair other = (NameArchPair) obj;
        return Objects.equals(getName(), other.getName())
        && Objects.equals(getArch(), other.getArch());
    }

    @Override
    public int hashCode () {
        return this.hashCode;
    }
}
