<?php

	// $file_path ="img/";
	

	// if(isset($_POST['submiy'])){
	// 	$file_path1 = $file_path.basename($_FILES['uploaded_file']['name']);
	// 	if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path1)){
	// 	echo $_FILES['uploaded_file']['name'];
	// }else{
	// 	echo "Failed";
	// }
	// }






?>

<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
<body>
    <form method="post" action="" enctype="multipart/form-data">
        <input type="file" name="avatar"/>
        <input type="submit" name="uploadclick" value="Upload"/>
    </form>
    <?php // Xử Lý Upload
  
    // Nếu người dùng click Upload
    if (isset($_POST['uploadclick']))
    {

    // $file_path = $file_path.basename($_FILES['uploaded_file']['name']);

    if(move_uploaded_file($_FILES['avatar']['tmp_name'],'/Applications/XAMPP/xamppfiles/htdocs/TTCN_NGODANGHIEU/folder/'.$_FILES['avatar']['name'])){
        echo $_FILES['avatar']['name'];
    }else{
        echo "Failed";
    }



        // Nếu người dùng có chọn file để upload
        // if (isset($_FILES['avatar']))
        // {
        //     // Nếu file upload không bị lỗi,
        //     // Tức là thuộc tính error > 0
        //     if ($_FILES['avatar']['error'] > 0)
        //     {
        //         echo 'File Upload Bị Lỗi';
        //     }
        //     else{
        //         // Upload file
        //         move_uploaded_file($_FILES['avatar']['tmp_name'], '/Applications/XAMPP/xamppfiles/htdocs/TTCN_NGODANGHIEU/folder'.$_FILES['avatar']['name']);
        //         echo 'File Uploaded';
        //     }
        // }
        // else{
        //     echo 'Bạn chưa chọn file upload';
        // }
    }
?>
</body>
</html>