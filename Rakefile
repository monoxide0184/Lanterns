
require 'fileutils'

def mcp_dir
  @mcp_dir ||= File.realpath ".."
end
mcp_dir

def mod_dir
  @mod_dir ||= File.realpath "."
end
mod_dir

def python
  `which python2 2> /dev/null`.chomp || `which python 2> /dev/null`.chomp
end

def batch(script, *params)
  script = "./#{script}.sh"
  if !File.executable?(script)
    File.chmod(0755, script)
  end
  sh script, *params
end

task :setup do
  Dir.chdir "#{mcp_dir}/forge" do batch "install" end
  Dir.chdir "#{mod_dir}/src" do
    Dir["*/*"].each do |dir|
      File.symlink(File.realpath(dir), "#{mcp_dir}/src/#{dir}")
    end
  end
end

desc "Package a new deployment"
task :deploy do
  Dir.chdir mcp_dir do
    batch "recompile"
    batch "reobfuscate"
  end
  zip_file = "#{mod_dir}/Lanterns-universal-1.0.0.jar"
  Dir.chdir "#{mcp_dir}/reobf/minecraft" do
    sh "zip", "-q", zip_file, *Dir["*"]
  end
  Dir["#{mod_dir}/src/*"].each do |dir|
    Dir.chdir dir do
      files = Dir["**/*"].reject {|f| File.directory? f }.reject {|f| f[-5, 5] == ".java" }
      if not files.empty?
        sh "zip", "-q", zip_file, *files
      end
    end
  end
end

