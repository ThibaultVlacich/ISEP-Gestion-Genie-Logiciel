const elixir     = require('laravel-elixir'),
      livereload = require('gulp-livereload')

require('laravel-elixir-imagemin')

const paths = {
  'SOURCE': 'src/main/webapp/resources',
  'DESTINATION': 'src/main/webapp/public',
  'VIEWS': 'src/main/WEB-INF',
  'BOOTSTRAP': 'node_modules/bootstrap-sass'
}

// Config Elixir
elixir.config.assetsPath    = paths.SOURCE
elixir.config.publicPath    = paths.DESTINATION
elixir.config.notifications = false

elixir(mix => {
  // Compile main SASS file
  mix.sass('app.scss')
     // Compile main JS file
     .browserify('app.js')
     .browserify('user.js')
     // Optimize then copy image files to the public dir
     .imagemin(paths.SOURCE+'/images', paths.DESTINATION+'/images')
     // Copy Bootstrap fonts to the public dir
     .copy(paths.BOOTSTRAP+'/assets/fonts', paths.DESTINATION+'/fonts')
})

/**
 * Logic for LiveReload to work properly on watch task.
 */
gulp.on('task_start', e => {
  // only start LiveReload server if task is 'watch'
  if (e.task === 'watch') {
    livereload.listen()
  }
})

gulp.on('task_stop', e => {
  if (e.task === 'sass') {
    // notify a CSS change, so that livereload can update it without a page refresh
    livereload.changed('app.css')
  } else if (e.task === 'browserify') {
    // notify a JS change, so that livereload can refresh the page
    livereload.changed('app.js')
  }
})
