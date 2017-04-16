const elixir = require('laravel-elixir');

require('laravel-elixir-imagemin');

const paths = {
  'SOURCE': './src/main/webapp/resources',
  'DESTINATION': './src/main/webapp/public'
};

// Config Elixir
elixir.config.assetsPath = paths.SOURCE;
elixir.config.publicPath = paths.DESTINATION;
elixir.config.notifications = false;

elixir(function (mix) {
  // Compile main SASS file
  mix.sass('app.scss')
     // Compile main JS file
     .browserify('app.js')
     // Optimize then copy image files to the public dir
     .imagemin(paths.SOURCE+'/images', paths.DESTINATION+'/images')
     // Copy Bootstrap fonts to the public dir
     .copy(paths.SOURCE+'/bootstrap/fonts', paths.DESTINATION+'/fonts');
});
