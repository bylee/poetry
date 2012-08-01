window.View = Backbone.View.extend( {
	template: function( model ) {
		var temp = $(this.templateId).html();
		if ( !model ) {
			return temp;
		}
		var result = Mustache.render( temp, model.toJSON() );
		console.log( "Add menu :%s", result );
		return result;
	}
} );

window.MenuItem = Backbone.Model.extend( {
	
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

window.ImageUploadView = View.extend( {
	templateId: "#imageUpload"
	
	
} );
