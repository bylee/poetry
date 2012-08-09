window.Model = Backbone.Model.extend( {
	
} );
window.View = Backbone.View.extend( {
	template: function( model ) {
		var temp = this.defaultTemplate;
		if ( this.templateId ) {
			temp = $(this.templateId).html();
			if ( !temp ) {
				temp = this.defaultTemplate;
			}
		}
		if ( !model ) {
			return temp;
		}
		var result = Mustache.render( temp, model.toJSON() );
		console.log( "create template :%s", result );
		return result;
	}
} );
window.Collection = Backbone.Collection.extend( { } );

window.MenuItem = Model.extend( {
	
} );
window.MenuItemView = View.extend( {
	tagName: "li",
	templateId: "#menu-item-template",
	events: { "click" : "select" },
	render: function() {
		$(this.el).html( this.template( this.model ) );
		return this;
	},
	
	select: function( e ) {
		this.menu.select( this );
		return false;
	}
} );

window.Menu = Collection.extend( {
	model: MenuItem
	
} );

window.MenuView = View.extend( {
	templateId: "#menu-template",

	initialize: function() {
		this.collection.bind( 'add', this.add, this );
		this.collection.bind( 'remove', this.remove );
		$( this.el ).append( $( this.template( this.model ) ) );
		this.collection.each( this.add, this );
	},
	render: function () {
		return this;
	},
	add: function( model ) {
		itemView = new MenuItemView( { model: model } );
		itemView.menu = this;
		$(this.el).find( "ul" ).append( itemView.render().el );
		if ( !this.selectedItem ) {
			this.select( itemView );
		}
	},
	
	select: function( item ) {
		$( this.el ).find( "li" ).removeClass( "active" );
		$( item.el ).addClass( "active " );
		this.selectedItem = item;
		$.get( item.model.get( "url" ), function( data ){
			$( "#contents" ).html( data );
		} );
	}
} );

window.ImageUpload = Model.extend( {
	
} );
window.ImageUploadView = View.extend( {
	templateId: "#imageUpload-template",
	addChangeListener: function( listener )
	{
		
	}
		
} );


window.Calendar = Model.extend( {
	defaults: { date: new Date() },
	initialize: function() {
		if ( !this.get( "date" ) ) {
			this.set( { "date": this.defaults.date } );
		}
	},
	setDate: function( date ) {
		this.set( { "date": date } );
	},
	getDate: function() {
		return this.get( "date" );
	}
} );

window.CalendarView = View.extend( {
	format: "yyyy-mm-dd",
	templateId: "#date-template",
	defaultTemplate:'<input name="date" type="text">',
	initialize: function() {
		$( this.el ).append( $( this.template( this.model ) ) );
		_.bindAll( this, "changeDate" );
		this.model.bind( "change:date", this.changeDate );
		
		this.picker = $( 'input' ).datepicker( { "format" : this.format } );
		
		this.picker.on( "changeDate", _.bind( function( ev ) {
			this.model.set( { "date" : ev.date, silent : true } );
		}, this ) );
		this.picker.val( this.formatDate( this.model.getDate() ) );
	},
	
	render: function() {
		this.picker.datepicker( "update" );
		return this;
	},
	
	changeDate: function( model ) {
		this.picker.val( this.formatDate( this.model.getDate() ) );
		this.render();
	},
	
	formatDate: function( date ) {
		return DPGlobal.formatDate( date, DPGlobal.parseFormat( this.format ) );
	}

} );

window.ChoiceItem = Model.extend( {
} );

window.ChoiceItemView = View.extend( {
	tagName: "label",
	className: "radio",
	templateId: "#choice-item-template",
	defaultTemplate: '<input type="radio" name="{{group}}" id="{{id}}" value="{{value}}">{{name}}',
	events: { "click" : "select" },
	initialize: function() {
		this.model.bind( 'change', this.update, this );
	},
	update: function() {
		$( this.el ).html( this.template( this.model ) );
		if ( this.model == this.choice.getSelection() ) {
			$( this.el ).find( "input" ).attr( "checked", true );
		}
	},
	render: function() {
		this.update();
		return this;
	},
	
	select: function( e ) {
		this.choice.setSelection( this.model );
	}
} );

window.Choice = Collection.extend( {
	model: ChoiceItem,
	setSelection: function( selection ) {
		if ( !selection ) {
			selection = this.models[0];
		}
		if ( this.selection == selection ) {
			return ;
		}
		if ( this.selection ) {
			this.selection.trigger( "change" );
		}
		this.selection = selection;
		this.selection.trigger( "change" );
		this.selection.get( "act" )();
	},
	getSelection: function() {
		return this.selection;
	},
	isSelected: function() {
		return ( undefined != this.selection );
	}

} );

window.ChoiceView = View.extend( {
	templateId: "#choice-template",
	id: _.uniqueId( 'choice' ),

	initialize: function() {
		this.collection.bind( 'add', this.add, this );
		this.collection.bind( 'remove', this.remove );
		$( this.el ).append( $( this.template( this.model ) ) );
		this.collection.each( this.add, this );
		
	},
	render: function () {
		return this;
	},
	add: function( model ) {
		model.set( "group", this.id );
		itemView = new ChoiceItemView( { model: model } );
		itemView.choice = this.collection;
		$(this.el).append( itemView.render().el );
		if ( !this.collection.isSelected() )
		{
			this.collection.setSelection( model );
		}
	}
} );

window.Poetry = Model.extend( {
} );

window.PoetryView = View.extend( {
	className:"item",
	templateId: "#poetry-template",
	defaultTemplate: '<img width="200", height="200" src="../../binary/{{image}}"><div>{{contents}}</div>',
	initialize: function() {
		this.model.bind( 'change', this.update, this );
	},
	render: function () {
		this.update();
		return this;
	},
	update: function() {
		$( this.el ).html( this.template( this.model ) );
		if ( this.model == this.poetries.getSelection() ) {
			$( this.el ).addClass( "active" );
		}

	}
	
	
} );

window.Poetries = Collection.extend( {
	model: Poetry,
	setSelection: function( selection ) {
		if ( !selection ) {
			selection = this.models[0];
		}
		if ( this.selection == selection ) {
			return ;
		}
		if ( this.selection ) {
			this.selection.trigger( "change" );
		}
		this.selection = selection;
		this.selection.trigger( "change" );
	},
	getSelection: function() {
		return this.selection;
	},
	isSelected: function() {
		return ( undefined != this.selection );
	},
	nextRequest: function() {
		url = "../../today/candidate";
		if ( !_.isEmpty( this.models ) ) {
			url += "?start=" + _.last( this.models ).get( "id" );
		}
		$.ajax( {
			method: "GET",
			dataType: "json",
			url: url,
			context: this,
			success: this.nextResponse
		} );
	},
	nextResponse: function( json ) {
		_.each( json, function( m, l ) {
			this.add( new Poetry( m ) );
		}, this );
	},
	
	
	
} );

window.Carousel = View.extend( {
	templateId: "#carousel-template",
	defaultTemplate: '<div class="carousel slide" style="height: 220px;"><div class="carousel-inner"></div><a class="carousel-control left" data-slide="prev">&lsaquo;</a><a class="carousel-control right" data-slide="next">&rsaquo;</a></div>',
	initialize: function() {
		this.collection.bind( 'add', this.add, this );
		this.collection.bind( 'remove', this.remove );
		$( this.el ).html( $( this.template( this.model ) ) );
		$( this.el ).attr( "id", _.uniqueId( 'carousel' ) );
		$( this.el ).find( "a" ).attr( "href", "#" + $( this.el ).attr( "id" ) );
		
		this.collection.each( this.add, this );
	},
	render: function () {
		$( this.el ).carousel( { interval: 5000 } );
		return this;
	},
	add: function( model ) {
		itemView = new PoetryView( { model: model } );
		itemView.poetries = this.collection;
		$(this.el).find( ".carousel-inner" ).append( itemView.render().el );
		if ( !this.collection.isSelected() )
		{
			this.collection.setSelection( model );
		}
	}
	
} );

