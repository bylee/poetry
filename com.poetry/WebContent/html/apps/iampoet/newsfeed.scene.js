function initScene() {
  var scene = this.getScene();
  scene.setStyles({
    backgroundColor : 'transparent',
    backgroundImage : 'none'
  });
  var scrollPanel1 = new tau.ui.ScrollPanel({
    id : 'mainPanel'
  });
  scene.add(scrollPanel1);
  
}