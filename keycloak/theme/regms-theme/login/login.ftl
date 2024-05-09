<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>RegmsClient</title>
    <base href="/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${url.resourcesPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${url.resourcesPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${url.resourcesPath}/css/custom-login.css">
    <script>
        document.documentElement.setAttribute('data-theme', "dark");
        function changeLogoTheme() {
            var logo = document.querySelector('img');
            var currentTheme = document.documentElement.getAttribute('data-theme');

            if (currentTheme === 'dark') {
                logo.src = "${url.resourcesPath}/assets/regms-logo-light.png";
            }
            else {
                logo.src = "${url.resourcesPath}/assets/regms-logo.png";
            }
        }
        document.addEventListener('DOMContentLoaded', changeLogoTheme);
        document.documentElement.addEventListener('data-theme-change', changeLogoTheme);
    </script>
</head>
<body>
<div class="page-container">
    <div class="row justify-content-center text-center mt-5">
        <div class="col-sm-10 col-md-1 col-lg-4 position-relative z-index-1">
            <div class="card card-body p-4 p-sm-4 mt-sm-n5 mb-n5 bg-transparent">
                <div class="mb-4">
                    <img alt="" src="${url.resourcesPath}/assets/regms-logo-light.png" height="50" width="137"/>
                </div>
                <h1 class="fs-2 login-title">Member Login</h1>
                <form class="mt-4" action="${url.loginAction}" method="post">
                    <!-- Username -->
                    <div class="mb-3 position-relative">
                        <input type="text" class="form-control" placeholder="Enter username" id="username" name="username" />
                    </div>
                    <!-- Password -->
                    <div class="mb-3">
                        <div class="input-group">
                            <input class="form-control fakepassword" type="password" id="password" placeholder="Enter password" name="password" />
                        </div>
                    </div>
                    <!-- Button -->
                    <div class="d-grid">
                        <button type="submit" class="btn btn-lg btn-primary-soft mt-3">
                            Login
                        </button>
                    </div>
                    <div class="bottom-text">
                        <p class="mb-0 mt-4 register-account-text">Don't have an account? <p class="form-url">Click here to sign up</p></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container">
        <footer class="py-2">
            <div class="d-flex flex-column flex-sm-row justify-content-between py-4 my-4 footer-border">
                <p class="footer-text">© 2024 Company</p>
            </div>
        </footer>
    </div>
</div>
<script src="${url.resourcesPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
