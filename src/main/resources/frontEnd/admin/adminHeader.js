window.onload = function ()
{
    var header = document.getElementById('header');
    header.innerHTML = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "    <style>\n" +
        "        body {\n" +
        "            font-family: \"Lato\", sans-serif;\n" +
        "        }\n" +
        "\n" +
        "        .sidenav {\n" +
        "            height: 100%;\n" +
        "            width: 250px;\n" +
        "            position: fixed;\n" +
        "            z-index: 1;\n" +
        "            top: 0;\n" +
        "            left: 0;\n" +
        "            background-color: #0dd354;\n" +
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
        "    <h1 style=\"padding-left: 30px\">site name</h1>\n" +
        "\n" +
        "\n" +
        "    <a href=\"admin.html\">Dashboard</a>\n" +
        "    <a href=\"users.html\">Users</a>\n" +
        "    <a href=\"products.html\">Products</a>\n" +
        "    <a href=\"topproducts.html\">Top 10 Product</a>\n" +
        "    <a href=\"index.html\">User View</a>\n" +
        "</div>\n" +
        "\n" +
        "</body>\n" +
        "</html>\n"
}