<?php

require 'connect.php';
	$ID =$_POST['ID'];
	$MK =$_POST['MK'];
	// $ID =2;
	// $MK =5;
	$query ="UPDATE tbl_user SET pass_user='$MK' WHERE id=$ID";
	if(mysqli_query($connect,$query)){

		echo"success";
	}else{
		echo"error";
	}

?>