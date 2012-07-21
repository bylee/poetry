function initScene() {
  var scene = this.getScene();
  var scrollPanel1 = new tau.ui.ScrollPanel({
    id : 'mainPanel',
    pullToRefresh : 'up'
  });
  scene.add(scrollPanel1);
  var panel1 = new tau.ui.Panel(
      {
        id : 'missionImageInfo',
        styles : {
          backgroundColor : '#FFFFFF',
          backgroundImage : '-webkit-gradient(linear, left top, right top,from(#000000),to(#FFFFFF))',
          height : '150px',
          width : '100%'
        }
      });
  scrollPanel1.add(panel1);
  var imageView1 = new tau.ui.ImageView({
    src : '/image/person.jpeg',
    styles : {
      borderColor : '#CCCCCC',
      borderStyle : 'solid',
      borderWidth : '2px',
      display : 'inline',
      height : '32px',
      width : '32px'
    }
  });
  panel1.add(imageView1);
  var button1 = new tau.ui.Button({
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      borderStyle : 'none'
    },
    label : {
      normal : 'Big Picture James'
    }
  });
  panel1.add(button1);
  var label1 = new tau.ui.Label({
    text : 'updated 2012.07',
    styles : {
      bottom : '2px',
      fontSize : '11px',
      position : 'absolute',
      right : '5px'
    }
  });
  panel1.add(label1);
  var panel2 = new tau.ui.Panel({
    align : 'center',
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      height : '110px',
      margin : '',
      padding : '10px',
      position : '',
      textAlign : '',
      width : '100%'
    }
  });
  panel1.add(panel2);
  var textView1 = new tau.ui.TextView(
      {
        text : '  이 사진은 강원도 지역의 시골길을 찍은 것이며, 멀리 보이는 길을 가르쳐주는 가을 나무들이 있는 풍경입니다. 날짜는 2002년 10월경입니다.',
        styles : {
          WebkitBorderRadius : '2px',
          backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
          display : 'inline-block',
          fontSize : '15px',
          margin : '',
          width : ''
        }
      });
  panel2.add(textView1);
  var imageView2 = new tau.ui.ImageView({
    id : 'missionImage',
    src : '/image/fallroad.jpeg',
    styles : {
      height : '150px',
      width : '100%'
    }
  });
  scrollPanel1.add(imageView2);
  var carousel1 = new tau.ui.Carousel({
    id : 'missionPanel',
    styles : {
      height : '150px'
    }
  });
  scrollPanel1.add(carousel1);
  var panel3 = new tau.ui.Panel({
    id : 'missionPoem',
    styles : {
      WebkitBorderRadius : '5px',
      borderStyle : 'solid',
      borderWidth : '2px',
      marginTop : '10px',
      width : '100%'
    }
  });
  scrollPanel1.add(panel3);
  var panel4 = new tau.ui.Panel(
      {
        styles : {
          backgroundColor : '#FFFFFF',
          backgroundImage : '-webkit-gradient(linear, left top, right top,from(#FF0A05),to(#FFFFFF))',
          height : '150px',
          width : '100%'
        }
      });
  panel3.add(panel4);
  var imageView3 = new tau.ui.ImageView({
    src : '/image/person.jpeg',
    styles : {
      borderColor : '#CCCCCC',
      borderStyle : 'solid',
      borderWidth : '2px',
      display : 'inline',
      height : '32px',
      width : '32px'
    }
  });
  panel4.add(imageView3);
  var button2 = new tau.ui.Button({
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      borderStyle : 'none'
    },
    label : {
      normal : 'Kobe bryant'
    }
  });
  panel4.add(button2);
  var label2 = new tau.ui.Label({
    style : 'position: static',
    text : '..more',
    styles : {
      bottom : '2px',
      color : '#3399FF',
      fontSize : '12px',
      left : '10px',
      position : 'absolute'
    }
  });
  panel4.add(label2);
  var label3 = new tau.ui.Label({
    text : 'updated 2012.07',
    styles : {
      bottom : '2px',
      fontSize : '11px',
      position : 'absolute',
      right : '5px'
    }
  });
  panel4.add(label3);
  var panel5 = new tau.ui.Panel({
    align : 'center',
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      height : '110px',
      margin : '',
      padding : '10px',
      position : '',
      textAlign : '',
      width : '100%'
    }
  });
  panel4.add(panel5);
  var textView2 = new tau.ui.TextView(
      {
        id : 'pPoem1',
        text : '움켜쥔 손 안의 모래알처럼 시간이 새고 있다 \n 집착이란 이처럼 허망한 것이다. \n 그렇게 네가 가고 나면 내게 남겨진 가을은 \n  김장끝난 텃밭에 싸락눈을 불러올 것이다. \n \n 문장이 되지못한 말(語)들이\n  반쯤 걷다가 바람의 뒷발에 채인다.\n 추억이란 아름답지만 때로는 치사한 것\n 먼 훗 날 내 가슴의 터엔 회한의 먼지만이 붐빌 것이다.\n',
        styles : {
          WebkitBorderRadius : '2px',
          backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
          display : 'inline-block',
          fontSize : '13px',
          margin : '',
          width : ''
        }
      });
  panel5.add(textView2);
  var panel6 = new tau.ui.Panel({
    styles : {
      WebkitBoxOrient : 'Horizontal',
      WebkitBoxPack : 'center',
      display : '-webkit-box',
      position : '',
      width : '100%'
    }
  });
  panel3.add(panel6);
  var button3 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      borderStyle : 'none',
      display : '-webkit-box'
    },
    label : {
      normal : 'Good'
    }
  });
  panel6.add(button3);
  var button4 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      backgroundColor : '#FFFFFF',
      backgroundImage : "url('/image/addcomment.gif')",
      backgroundRepeat : 'no-repeat',
      borderStyle : 'none',
      color : 'transparent',
      display : '-webkit-box',
      width : '48px'
    }
  });
  panel6.add(button4);
  var panel7 = new tau.ui.Panel({
    styles : {
      WebkitBoxFlex : 4,
      backgroundColor : '#FFFFFF',
      width : '180px'
    }
  });
  panel6.add(panel7);
  var button5 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      borderStyle : 'none',
      display : '-webkit-box'
    },
    label : {
      normal : 'G 27'
    }
  });
  panel6.add(button5);
  var button6 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      backgroundColor : '#FFFFFF',
      backgroundImage : "url('/image/comments.gif')",
      backgroundPosition : 'left center',
      backgroundSize : '50%',
      borderColor : 'transparent',
      borderStyle : 'none',
      color : '#516691',
      display : '-webkit-box',
      textAlign : 'right',
      width : '78px'
    },
    label : {
      normal : '19'
    }
  });
  panel6.add(button6);
  var panel8 = new tau.ui.Panel({
    id : 'missionPoem',
    styles : {
      WebkitBorderRadius : '5px',
      borderStyle : 'solid',
      borderWidth : '2px',
      marginTop : '10px',
      width : '100%'
    }
  });
  scrollPanel1.add(panel8);
  var panel9 = new tau.ui.Panel(
      {
        styles : {
          backgroundColor : '#FFFFFF',
          backgroundImage : '-webkit-gradient(linear, left top, right top,from(#FF0A05),to(#FFFFFF))',
          height : '150px',
          width : '100%'
        }
      });
  panel8.add(panel9);
  var imageView4 = new tau.ui.ImageView({
    src : '/image/person.jpeg',
    styles : {
      borderColor : '#CCCCCC',
      borderStyle : 'solid',
      borderWidth : '2px',
      display : 'inline',
      height : '32px',
      width : '32px'
    }
  });
  panel9.add(imageView4);
  var button7 = new tau.ui.Button({
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      borderStyle : 'none'
    },
    label : {
      normal : 'Kobe bryant'
    }
  });
  panel9.add(button7);
  var label4 = new tau.ui.Label({
    style : 'position: static',
    text : '..more',
    styles : {
      bottom : '2px',
      color : '#3399FF',
      fontSize : '12px',
      left : '10px',
      position : 'absolute'
    }
  });
  panel9.add(label4);
  var label5 = new tau.ui.Label({
    text : 'updated 2012.07',
    styles : {
      bottom : '2px',
      fontSize : '11px',
      position : 'absolute',
      right : '5px'
    }
  });
  panel9.add(label5);
  var panel10 = new tau.ui.Panel({
    align : 'center',
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      height : '110px',
      margin : '',
      padding : '10px',
      position : '',
      textAlign : '',
      width : '100%'
    }
  });
  panel9.add(panel10);
  var textView3 = new tau.ui.TextView(
      {
        id : 'pPoem2',
        text : '움켜쥔 손 안의 모래알처럼 시간이 새고 있다 \n 집착이란 이처럼 허망한 것이다. \n 그렇게 네가 가고 나면 내게 남겨진 가을은 \n  김장끝난 텃밭에 싸락눈을 불러올 것이다. \n \n 문장이 되지못한 말(語)들이\n  반쯤 걷다가 바람의 뒷발에 채인다.\n 추억이란 아름답지만 때로는 치사한 것\n 먼 훗 날 내 가슴의 터엔 회한의 먼지만이 붐빌 것이다.\n',
        styles : {
          WebkitBorderRadius : '2px',
          backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
          display : 'inline-block',
          fontSize : '13px',
          margin : '',
          width : ''
        }
      });
  panel10.add(textView3);
  var panel11 = new tau.ui.Panel({
    styles : {
      WebkitBoxOrient : 'Horizontal',
      WebkitBoxPack : 'center',
      display : '-webkit-box',
      position : '',
      width : '100%'
    }
  });
  panel8.add(panel11);
  var button8 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      borderStyle : 'none',
      display : '-webkit-box'
    },
    label : {
      normal : 'Good'
    }
  });
  panel11.add(button8);
  var button9 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      backgroundColor : '#FFFFFF',
      backgroundImage : "url('/image/addcomment.gif')",
      backgroundRepeat : 'no-repeat',
      borderStyle : 'none',
      color : 'transparent',
      display : '-webkit-box',
      width : '48px'
    }
  });
  panel11.add(button9);
  var panel12 = new tau.ui.Panel({
    styles : {
      WebkitBoxFlex : 4,
      backgroundColor : '#FFFFFF',
      width : '180px'
    }
  });
  panel11.add(panel12);
  var button10 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      borderStyle : 'none',
      display : '-webkit-box'
    },
    label : {
      normal : 'G 27'
    }
  });
  panel11.add(button10);
  var button11 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      backgroundColor : '#FFFFFF',
      backgroundImage : "url('/image/comments.gif')",
      backgroundPosition : 'left center',
      backgroundSize : '50%',
      borderColor : 'transparent',
      borderStyle : 'none',
      color : '#516691',
      display : '-webkit-box',
      textAlign : 'right',
      width : '78px'
    },
    label : {
      normal : '19'
    }
  });
  panel11.add(button11);
  var panel13 = new tau.ui.Panel({
    id : 'missionPoem',
    styles : {
      WebkitBorderRadius : '5px',
      borderStyle : 'solid',
      borderWidth : '2px',
      marginTop : '10px',
      width : '100%'
    }
  });
  scrollPanel1.add(panel13);
  var panel14 = new tau.ui.Panel(
      {
        styles : {
          backgroundColor : '#FFFFFF',
          backgroundImage : '-webkit-gradient(linear, left top, right top,from(#FF0A05),to(#FFFFFF))',
          height : '150px',
          width : '100%'
        }
      });
  panel13.add(panel14);
  var imageView5 = new tau.ui.ImageView({
    src : '/image/person.jpeg',
    styles : {
      borderColor : '#CCCCCC',
      borderStyle : 'solid',
      borderWidth : '2px',
      display : 'inline',
      height : '32px',
      width : '32px'
    }
  });
  panel14.add(imageView5);
  var button12 = new tau.ui.Button({
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      borderStyle : 'none'
    },
    label : {
      normal : 'Kobe bryant'
    }
  });
  panel14.add(button12);
  var label6 = new tau.ui.Label({
    style : 'position: static',
    text : '..more',
    styles : {
      bottom : '2px',
      color : '#3399FF',
      fontSize : '12px',
      left : '10px',
      position : 'absolute'
    }
  });
  panel14.add(label6);
  var label7 = new tau.ui.Label({
    text : 'updated 2012.07',
    styles : {
      bottom : '2px',
      fontSize : '11px',
      position : 'absolute',
      right : '5px'
    }
  });
  panel14.add(label7);
  var panel15 = new tau.ui.Panel({
    align : 'center',
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      height : '110px',
      margin : '',
      padding : '10px',
      position : '',
      textAlign : '',
      width : '100%'
    }
  });
  panel14.add(panel15);
  var textView4 = new tau.ui.TextView(
      {
        id : 'pPoem3',
        text : '움켜쥔 손 안의 모래알처럼 시간이 새고 있다 \n 집착이란 이처럼 허망한 것이다. \n 그렇게 네가 가고 나면 내게 남겨진 가을은 \n  김장끝난 텃밭에 싸락눈을 불러올 것이다. \n \n 문장이 되지못한 말(語)들이\n  반쯤 걷다가 바람의 뒷발에 채인다.\n 추억이란 아름답지만 때로는 치사한 것\n 먼 훗 날 내 가슴의 터엔 회한의 먼지만이 붐빌 것이다.\n',
        styles : {
          WebkitBorderRadius : '2px',
          backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
          display : 'inline-block',
          fontSize : '13px',
          margin : '',
          width : ''
        }
      });
  panel15.add(textView4);
  var panel16 = new tau.ui.Panel({
    styles : {
      WebkitBoxOrient : 'Horizontal',
      WebkitBoxPack : 'center',
      display : '-webkit-box',
      position : '',
      width : '100%'
    }
  });
  panel13.add(panel16);
  var button13 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      borderStyle : 'none',
      display : '-webkit-box'
    },
    label : {
      normal : 'Good'
    }
  });
  panel16.add(button13);
  var button14 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      backgroundColor : '#FFFFFF',
      backgroundImage : "url('/image/addcomment.gif')",
      backgroundRepeat : 'no-repeat',
      borderStyle : 'none',
      color : 'transparent',
      display : '-webkit-box',
      width : '48px'
    }
  });
  panel16.add(button14);
  var panel17 = new tau.ui.Panel({
    styles : {
      WebkitBoxFlex : 4,
      backgroundColor : '#FFFFFF',
      width : '180px'
    }
  });
  panel16.add(panel17);
  var button15 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      borderStyle : 'none',
      display : '-webkit-box'
    },
    label : {
      normal : 'G 27'
    }
  });
  panel16.add(button15);
  var button16 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      backgroundColor : '#FFFFFF',
      backgroundImage : "url('/image/comments.gif')",
      backgroundPosition : 'left center',
      backgroundSize : '50%',
      borderColor : 'transparent',
      borderStyle : 'none',
      color : '#516691',
      display : '-webkit-box',
      textAlign : 'right',
      width : '78px'
    },
    label : {
      normal : '19'
    }
  });
  panel16.add(button16);
  var panel18 = new tau.ui.Panel({
    id : 'missionPoem',
    styles : {
      WebkitBorderRadius : '5px',
      borderStyle : 'solid',
      borderWidth : '2px',
      marginTop : '10px',
      width : '100%'
    }
  });
  scrollPanel1.add(panel18);
  var panel19 = new tau.ui.Panel(
      {
        styles : {
          backgroundColor : '#FFFFFF',
          backgroundImage : '-webkit-gradient(linear, left top, right top,from(#FF0A05),to(#FFFFFF))',
          height : '150px',
          width : '100%'
        }
      });
  panel18.add(panel19);
  var imageView6 = new tau.ui.ImageView({
    src : '/image/person.jpeg',
    styles : {
      borderColor : '#CCCCCC',
      borderStyle : 'solid',
      borderWidth : '2px',
      display : 'inline',
      height : '32px',
      width : '32px'
    }
  });
  panel19.add(imageView6);
  var button17 = new tau.ui.Button({
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      borderStyle : 'none'
    },
    label : {
      normal : 'Kobe bryant'
    }
  });
  panel19.add(button17);
  var label8 = new tau.ui.Label({
    style : 'position: static',
    text : '..more',
    styles : {
      bottom : '2px',
      color : '#3399FF',
      fontSize : '12px',
      left : '10px',
      position : 'absolute'
    }
  });
  panel19.add(label8);
  var label9 = new tau.ui.Label({
    text : 'updated 2012.07',
    styles : {
      bottom : '2px',
      fontSize : '11px',
      position : 'absolute',
      right : '5px'
    }
  });
  panel19.add(label9);
  var panel20 = new tau.ui.Panel({
    align : 'center',
    styles : {
      backgroundColor : 'transparent',
      backgroundImage : 'none',
      height : '110px',
      margin : '',
      padding : '10px',
      position : '',
      textAlign : '',
      width : '100%'
    }
  });
  panel19.add(panel20);
  var textView5 = new tau.ui.TextView(
      {
        id : 'pPoem4',
        text : '움켜쥔 손 안의 모래알처럼 시간이 새고 있다 \n 집착이란 이처럼 허망한 것이다. \n 그렇게 네가 가고 나면 내게 남겨진 가을은 \n  김장끝난 텃밭에 싸락눈을 불러올 것이다. \n \n 문장이 되지못한 말(語)들이\n  반쯤 걷다가 바람의 뒷발에 채인다.\n 추억이란 아름답지만 때로는 치사한 것\n 먼 훗 날 내 가슴의 터엔 회한의 먼지만이 붐빌 것이다.\n',
        styles : {
          WebkitBorderRadius : '2px',
          backgroundImage : '-webkit-gradient(linear, left top, left bottom,from(#FFFFFF),to(#FFFFFF))',
          display : 'inline-block',
          fontSize : '13px',
          margin : '',
          width : ''
        }
      });
  panel20.add(textView5);
  var panel21 = new tau.ui.Panel({
    styles : {
      WebkitBoxOrient : 'Horizontal',
      WebkitBoxPack : 'center',
      display : '-webkit-box',
      position : '',
      width : '100%'
    }
  });
  panel18.add(panel21);
  var button18 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      borderStyle : 'none',
      display : '-webkit-box'
    },
    label : {
      normal : 'Good'
    }
  });
  panel21.add(button18);
  var button19 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      backgroundColor : '#FFFFFF',
      backgroundImage : "url('/image/addcomment.gif')",
      backgroundRepeat : 'no-repeat',
      borderStyle : 'none',
      color : 'transparent',
      display : '-webkit-box',
      width : '48px'
    }
  });
  panel21.add(button19);
  var panel22 = new tau.ui.Panel({
    styles : {
      WebkitBoxFlex : 4,
      backgroundColor : '#FFFFFF',
      width : '180px'
    }
  });
  panel21.add(panel22);
  var button20 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      borderStyle : 'none',
      display : '-webkit-box'
    },
    label : {
      normal : 'G 27'
    }
  });
  panel21.add(button20);
  var button21 = new tau.ui.Button({
    styles : {
      WebkitBorderRadius : '0px',
      backgroundColor : '#FFFFFF',
      backgroundImage : "url('/image/comments.gif')",
      backgroundPosition : 'left center',
      backgroundSize : '50%',
      borderColor : 'transparent',
      borderStyle : 'none',
      color : '#516691',
      display : '-webkit-box',
      textAlign : 'right',
      width : '78px'
    },
    label : {
      normal : '19'
    }
  });
  panel21.add(button21);
  var button22 = new tau.ui.Button({
    id : 'write',
    styles : {
      fontSize : '10px'
    },
    label : {
      normal : 'Write Mission'
    }
  });
  scene.add(button22);
  button22.onEvent('tap', this.handleWrite, this);
}