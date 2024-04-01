package restclient.restclient.models;

import java.util.ArrayList;
import java.util.List;

public class ArchPackagesMessage {
    private int length;
    private List<PackageMessage> packages = new ArrayList<PackageMessage>();

    public ArchPackagesMessage (PackageMessage pkg) {
        addPackage(pkg);
    }

    public void addPackage (PackageMessage pkg) {
        packages.add(pkg);
        length++;
    }

    public int getLength () {
        return length;
    }

    public void setLength (int length) {
        this.length = length;
    }

    public List<PackageMessage> getPackages() {
        return packages;
    }

    public void setPackages (List<PackageMessage> packages) {
        this.packages = packages;
    }
}
