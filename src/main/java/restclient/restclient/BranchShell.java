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
    public void branchDiff (
        @ShellOption(help = "first branch name") String branch1,
        @ShellOption(help = "second branch name") String branch2,
        @ShellOption(help = "JSON filename") String fileName
    ) {
        restService.branchDiff(branch1, branch2, fileName);
    }

    @ShellMethod(key = "branchDiffArch", value = "writes json containing all packages present in first branch, but not in second for specified branch")
    public void branchDiffArch (
        @ShellOption(help = "first branch name") String branch1,
        @ShellOption(help = "second branch name") String branch2,
        @ShellOption(help = "arch") String arch,
        @ShellOption(help = "JSON filename") String fileName
    ) {
        restService.branchDiff(branch1, branch2, arch, fileName);
    }

    @ShellMethod(key = "branchDiffVersion", value = "writes json containing packages which version in the first branch higher than in the second")
    public void branchDiffVersion (
        @ShellOption(help = "first branch name") String branch1,
        @ShellOption(help = "second branch name") String branch2,
        @ShellOption(help = "JSON filename") String fileName
    ) {
        restService.branchDiffVersion(branch1, branch2, fileName);
    }

    @ShellMethod(key = "combinedBranchDiff", value = "combined json of 'branchDiff' and 'branchDiffVersion'")
    public void combinedBranchDiff (
        @ShellOption(help = "first branch name") String branch1,
        @ShellOption(help = "second branch name") String branch2,
        @ShellOption(help = "JSON filename") String fileName
    ) {
        restService.combinedBranchDiff(branch1, branch2, fileName);
    }
}
