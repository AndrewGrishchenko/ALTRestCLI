package restclient.restclient.models;

import java.util.HashMap;

public class ResponseMessage {
    private String responseType;
    private String branch1;
    private String branch2;
    private int totalPackages;
    private HashMap<String, ArchPackagesMessage> arches = new HashMap<String, ArchPackagesMessage>();

    public ResponseMessage (String responseType) {
        this.responseType = responseType;
    }

    public void addPackage (PackageMessage pkg) {
        if (arches.containsKey(pkg.getArch())) {
            arches.get(pkg.getArch()).addPackage(pkg);
        } else {
            arches.put(pkg.getArch(), new ArchPackagesMessage(pkg));
        }
        totalPackages++;
    }

    public String getResponseType () {
        return responseType;
    }

    public String getBranch1 () {
        return branch1;
    }

    public String getBranch2 () {
        return branch2;
    }

    public void setBranches (String branch1, String branch2) {
        this.branch1 = branch1;
        this.branch2 = branch2;
    }

    public int getTotalPackages () {
        return totalPackages;
    }

    public HashMap<String, ArchPackagesMessage> getArches () {
        return arches;
    }
}
