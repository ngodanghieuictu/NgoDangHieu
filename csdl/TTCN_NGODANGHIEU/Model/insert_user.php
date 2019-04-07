<?php
	require 'connect.php';
	$TDN =$_POST['TDN'];
	$MK =$_POST['MK'];
	$LEVEL =$_POST['LEVEL'];
	$HVT =$_POST['HVT'];

	// $Ten_sp ="1234";
	// $Gia_sp ="1234";
	// $Sl_sp ="1234";
	// $Nsx_sp ="1234";
	// $Ngaynhap_sp ="1234";

	$query ="INSERT INTO tbl_user(namelogin_user,pass_user,level,name_user) VALUES('$TDN','$MK','$LEVEL','$HVT')";
	if(mysqli_query($connect,$query)){

		echo"success";
	}else{
		echo"error";
	}


?>