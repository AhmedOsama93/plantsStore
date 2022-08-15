window.onload = function ()
{
    var header = document.getElementById('header');
    header.innerHTML = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "    <style>\n" +
        "        body {\n" +
        "            font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;" +
        "        }\n" +
        "\n" +
        "        .sidenav {\n" +
        "            height: 100%;\n" +
        "            width: 250px;\n" +
        "            position: fixed;\n" +
        "            z-index: 1;\n" +
        "            top: 0;\n" +
        "            left: 0;\n" +
        "            background-color: #00B26F;\n" +
        "            overflow-x: hidden;\n" +
        "            padding-top: 20px;\n" +
        "        }\n" +
        "\n" +
        "        .sidenav a {\n" +
        "            padding: 30px 50px 6px 32px;\n" +
        "            text-decoration: none;\n" +
        "            font-size: 25px;\n" +
        "            color: #000000;\n" +
        "            display: block;\n" +
        "        }\n" +
        "\n" +
        "        .sidenav a:hover {\n" +
        "            color: #f1f1f1;\n" +
        "        }\n" +
        "\n" +
        "        .main {\n" +
        "            margin-left: 250px; /* Same as the width of the sidenav */\n" +
        "        }\n" +
        "\n" +
        "        @media screen and (max-height: 450px) {\n" +
        "            .sidenav {padding-top: 15px;}\n" +
        "            .sidenav a {font-size: 18px;}\n" +
        "        }\n" +
        "\n" +
        "    </style>\n" +
        "</head>\n" +
        "<body>\n" +
        "\n" +
        "<div class=\"sidenav\">\n" +
        "   <img src=\"../home/img/logo3.png\" class=\"logo\" style=\"width: 150px; height: 43px; \"> " +
        "\n" +
        "\n" +
        "    <a href=\"admin.html\"'><label style=\"font-size:18px; margin-left:20px; \">DASHBOARD</label></a>\n" +
        "    <a href=\"users.html\"><label style=\"font-size:18px;margin-left:20px; \">USERS</label></a>\n" +
        "    <a href=\"products.html\"><label style=\"font-size:18px;margin-left:20px; \">PRODUCTS</label></a>\n" +
        "    <a href=\"topproducts.html\"><label style=\"font-size:18px;margin-left:20px; \">TOP 10 PRODUCTS</label></a>\n" +
        "    <a href=\"allOrders.html\"><label style=\"font-size:18px; margin-left:20px;\">ORDERS</label></a>\n" +
        "    <a href=\"../home/index.html\"><label style=\"font-size:18px; margin-left:20px;\">USER VIEW</label></a>\n" +
        "</div>\n" +
        "\n" +
        "</body>\n" +
        "</html>\n"
}