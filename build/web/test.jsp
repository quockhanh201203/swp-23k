<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    </head>
    <body>
        <!-- Link truyền dữ liệu vào popup -->
        <a href="javascript:void(0);" onclick="openPopUp('John Doe')">Add to order</a>
        <br/>
        <a href="javascript:void(0);" onclick="openPopUp('Jane Smith')">Open Popup for Jane Smith</a>

        <!-- Popup ẩn chứa form -->
        <div id="popupDiv" style="display:none;" title="User Information">
            <form id="userForm" action="submitForm.jsp" method="post">
                UserName: <input type="text" id="userNameInput" name="userName"/><br/><br/>
                Email: <input type="email" id="userEmail" name="userEmail"/><br/><br/>
                <input type="submit" value="Submit"/>
            </form>
        </div>

        <script>
            // Hàm mở popup và đặt dữ liệu vào form
            function openPopUp(userName) {
                // Đặt giá trị của input userName vào form
                $('#userNameInput').val(userName);

                // Hiển thị popup với jQuery UI
                $('#popupDiv').dialog({
                    modal: true,
                    width: 400,
                    buttons: {
                        "Close": function() {
                            $(this).dialog("close");
                        }
                    }
                });
            }
        </script>
        <script>
            // Hàm mở popup và đặt dữ liệu vào form
            function openPopUp(userName) {
                // Đặt giá trị của input userName vào form
                $('#userNameInput').val(userName);

                // Hiển thị popup với jQuery UI
                $('#popupDiv').dialog({
                    modal: true,
                    width: 400,
                    buttons: {
                        "Close": function() {
                            $(this).dialog("close");
                        }
                    }
                });
            }
        </script>
    </body>
</html>
