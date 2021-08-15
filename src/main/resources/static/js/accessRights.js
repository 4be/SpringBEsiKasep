if (location.pathname == "/" && localStorage.getItem("token") != null) {
    location.href = "/hcms/";
} else if (location.pathname.substr(0, 5) == "/hcms" && localStorage.getItem("token") == null) {
    location.href = "/";
}

