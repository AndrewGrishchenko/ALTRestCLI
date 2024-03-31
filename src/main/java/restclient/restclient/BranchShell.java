package restclient.restclient;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BranchShell {
    private final RestService restService;

    public BranchShell (RestService restService) {
        this.restService = restService;
    }

    @ShellMethod(key = "branchDiff", value = "writes json containing all packages present in first branch, but not in second")
    public String branchDiff (
        @ShellOption(help = "first branch") String branch1,
        @ShellOption(help = "second branch") String branch2,
        @ShellOption(help = "JSON filename") String fileName
    ) {
        return restService.ExportBranchBinaryPackages(branch1, branch2, fileName);
    }

    @ShellMethod(key = "branchDiffArch", value = "writes json containing all packages present in first branch, but not in second for specified branch")
    public String branchDiffArch (
        @ShellOption(help = "first branch") String branch1,
        @ShellOption(help = "second branch") String branch2,
        @ShellOption(help = "arch") String arch,
        @ShellOption(help = "JSON filename") String fileName
    ) {
        return restService.ExportBranchBinaryPackages(branch1, branch2, arch, fileName);
    }
}
