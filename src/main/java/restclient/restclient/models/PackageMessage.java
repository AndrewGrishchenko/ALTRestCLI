package restclient.restclient.models;

public class PackageMessage {
    String name;
    int epoch;
    String version;
    String release;
    String arch;
    String disttag;
    int buildtime;
    String source;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getEpoch () {
        return epoch;
    }

    public void setEpoch (int epoch) {
        this.epoch = epoch;
    }

    public String getVersion () {
        return version;
    }

    public void setVersion (String version) {
        this.version = version;
    }

    public String getRelease () {
        return release;
    }

    public void setRelease (String release) {
        this.release = release;
    }

    public String getArch () {
        return arch;
    }

    public void setArch (String arch) {
        this.arch = arch;
    }

    public String getDisttag () {
        return disttag;
    }

    public void setDisttag (String disttag) {
        this.disttag = disttag;
    }

    public int getBuildtime () {
        return buildtime;
    }

    public void setBuildtime (int buildtime) {
        this.buildtime = buildtime;
    }

    public String getSource () {
        return source;
    }

    public void setSource (String source) {
        this.source = source;
    }

    @Override
    public String toString () {
        return "Package{name=" + name 
        + "\nepoch=" + String.valueOf(epoch)
        + "\nversion=" + version
        + "\nrelease=" + release
        + "\narch=" + arch
        + "\ndisttag=" + disttag
        + "\nbuildTime=" + String.valueOf(buildtime)
        + "\nsource=" + source + "}";
    }
}
