const elixir = require('laravel-elixir');

require('laravel-elixir-imagemin');

// Config Elixir
elixir.config.assetsPath = './src/main/webapp/resources';
elixir.config.publicPath = './src/main/webapp/public';
elixir.config.notifications = false;

elixir(function (mix) {
  mix
    // Compile main SASS file
    .sass('app.scss')
    // Compile main JS file
    .browserify('app.js')
    // Optimize then copy image files to the public dir
    .imagemin('./src/main/webapp/resources/images', './src/main/webapp/public/images')
    // Copy Bootstrap fonts to the public dir
    .copy('./src/main/webapp/resources/bootstrap/fonts', './src/main/webapp/public/fonts');
});
