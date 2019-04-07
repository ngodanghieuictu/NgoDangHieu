<?php
	require 'connect.php';
	$TSP =$_POST['TSP'];
	$SL =$_POST['SL'];
	$IDUSER =$_POST['IDUSER'];
	$NSX =$_POST['NSX'];
	$LOAISP =$_POST['LOAISP'];
	$IMG ='TTCN_NGODANGHIEU/img/hang/'.$_POST['IMG'];
	$GIA =$_POST['GIA'];

	$DATE=date("Y-m-d");

	// $Ten_sp ="1234";
	// $Gia_sp ="1234";
	// $Sl_sp ="1234";
	// $Nsx_sp ="1234";
	// $Ngaynhap_sp ="1234";

	$query ="INSERT INTO tbl_sp(ten_sp,sl_sp,ngaynhap,nhasx,id_user,img,loai_sp,gia) VALUES('$TSP','$SL','$DATE','$NSX','$IDUSER','$IMG','$LOAISP','$GIA')";
	if(mysqli_query($connect,$query)){

		echo"success";
	}else{
		echo"error";
	}





?>