<?php

require 'connect.php';
	$ID =$_POST['ID'];
	$SL =$_POST['SL'];
	$date=date("Y-m-d");
	// $ID =2;
	// $MK =5;
	$query ="UPDATE tbl_sp SET sl_sp=sl_sp-$SL WHERE id=$ID";
	$query2 ="INSERT INTO tbl_hangxuat(id_sp,sl,date_xuat) VALUES('$ID','$SL','$date')";
	$inser =mysqli_query($connect,$query2);
	if(mysqli_query($connect,$query)&&$inser){

		echo"success";
	}else{
		echo"error";
	}

?>