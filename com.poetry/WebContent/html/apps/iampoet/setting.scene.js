function initScene() {
	var scene = this.getScene();
	var table1 = new tau.ui.Table({id : 'main'});
	scene.add(table1);
	var tableCell1 = new tau.ui.TableCell({groupName : 'personal' , id : 'pInfo' , title : '개인정보'});
	table1.add(tableCell1);
	var tableCell2 = new tau.ui.TableCell({groupName : 'personal' , id : 'following' , subTitle : '(255)' , title : 'Following'});
	table1.add(tableCell2);
	var tableCell3 = new tau.ui.TableCell({groupName : 'personal' , id : 'follower' , subTitle : '(1007)' , title : 'Follower'});
	table1.add(tableCell3);
	var tableCell4 = new tau.ui.TableCell({groupName : 'poem' , id : 'histroy' , subTitle : '작성된 시를 타임라인으로 볼 수 있습니다.' , title : '나의 시'});
	table1.add(tableCell4);
	var tableCell5 = new tau.ui.TableCell({groupName : 'setting' , id : 'nofi' , subTitle : '알림을 켜고 끌 수 있습니다.' , title : 'Notification'});
	table1.add(tableCell5);
	var tableCell6 = new tau.ui.TableCell({subTitle : '계정 탈퇴'});
	table1.add(tableCell6);
}