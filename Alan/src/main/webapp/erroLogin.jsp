<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sem permissão</title>
        <meta charset="UTF-8">
        <c:url var="pathResources" value="/resources" /> <%-- CORRIGE URL DO SISTEMA PARA ACESSAR O DIRETÓRIO RESOURCES --%>
        <link rel="stylesheet" type="text/css" href="${pathResources}/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pathResources}/css/style.css"/>
    </head>
    <body>
        <div class="container">
            <div class="jumbotron">
                <h1><i class="fa fa-ban red"></i> Sem permissão</h1>
                <p class="lead">Você não tem permissão de acesso a essa página, entre em contato com o Administrador do Sistema. <em><span id="display-domain"></span></em>.</p>
                <p><a onclick=javascript:checkSite(); class="btn btn-default btn-lg green">UI</a>
                    <script type="text/javascript">
                        function checkSite() {
                            var currentSite = window.location.hostname;
                            window.location = "http://" + currentSite;
                        }
                    </script>
                </p>
            </div>
        </div>
<!--        <div class="container">
            <div class="body-content">
                <div class="row">
                    <div class="col-md-6">
                        <h2>O que aconteceu?</h2>
                        <p class="lead">A 403 error status indicates that you don't have permission to access the file or page. In general, web servers and websites have directories and files that are not open to the public web for security reasons.</p>
                    </div>
                    <div class="col-md-6">
                        <h2>What can I do?</h2>
                        <p class="lead">If you're a site visitor</p>
                        <p>Please use your browsers back button and check that you're in the right place. If you need immediate assistance, please send us an email instead.</p>
                        <p class="lead">If you're the site owner</p>
                        <p>Please check that you're in the right place and get in touch with your website provider if you believe this to be an error.</p>
                    </div>
                </div>
            </div>
        </div>-->
    </body>
</html>