
require 'fileutils'

def mcp_dir
  @mcp_dir ||= (ENV["MCP_DIR"] || File.realpath(".."))
end
mcp_dir

def mod_dir
  @mod_dir ||= File.realpath "."
end
mod_dir

def platform
  @platform ||=
    case RbConfig::CONFIG['host_os']
    when /mingw|mswin/
      :windows
    when /linux/
      :linux
    else
      :unknown
    end
end

def python
  case platform
  when :linux
	`which python2 2> /dev/null`.chomp || `which python 2> /dev/null`.chomp
  when :windows
    "#{mcp_dir}\\runtime\\bin\\python\\python_mcp"
  end
end

def batch(script, *params)
  case platform
  when :linux
    script = "./#{script}.sh"
  when :windows
    if File.exist? "#{script}.bat"
      script = "#{script}.bat"
	else
	  script = "#{script}.cmd"
	end
  end
  if !File.executable?(script)
    File.chmod(0755, script)
  end
  sh script, *params
end

def symlink_cross_platform(old, new)
  case platform
  when :linux
    File.symlink(old, new)
  when :windows
	old.gsub!('/', '\\')
	new.gsub!('/', '\\')
	if File.directory? old
	  opts = ["/J", new, old]
	else
	  opts = [new, old]
	end
    sh "cmd.exe", "/c", "mklink", *opts
  end
end

task :setup do
  Dir.chdir "#{mcp_dir}/forge" do batch "install" end
  sh "git", "checkout", "src"
  Dir.chdir "#{mod_dir}/src" do
    Dir["*/*"].each do |dir|
      symlink_cross_platform(File.realpath(dir), "#{mcp_dir}/src/#{dir}")
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
    sh "zip", "-rq", zip_file, *Dir["*"]
  end

  if ENV['BUILD_NUMBER']
	sh "sed", "-rie", "s/\"version\": \"([0-9.]*\\.)([0-9]*)\"/\"version\": \"\\1#{ENV['BUILD_NUMBER']}\"/", "src/common/mcmod.info"
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
