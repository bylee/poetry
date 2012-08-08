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
		console.log( "Add menu :%s", result );
		return result;
	}
} );

window.MenuItem = window.Model.extend( {
	
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

window.Menu = Backbone.Collection.extend( {
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
