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

    @ShellMethod(key = "hello-to", value = "Say hello to username")
    public String helloTo(@ShellOption({"username", "u"}) String username) {
        return restService.helloTo(username);
    }

    @ShellMethod(key = "get", value = "get")
    public String get() {
        return restService.ExportBranchBinaryPackages("p9");
    }
}
