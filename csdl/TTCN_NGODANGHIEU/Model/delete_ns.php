<?php
	$id=$_POST['ID'];
	require 'connect.php';
	$query ="DELETE FROM tbl_user WHERE id='$id'";
	if(mysqli_query($connect,$query)){

		echo"success";
	}else{
		echo"error";
	}
?>